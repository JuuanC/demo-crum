package security;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.enterprise.context.RequestScoped;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@RequestScoped
/**
 * Encargado de proveer de servicios de codificacion de contraseñas al sistema. Utilizando propiedades del sistema
 * para generar y configurar la encriptacion de cadenas de texto. Su principal uso se encuentra en la decodificación de las
 * contraseñas de los nuevos usuarios registrados del sistema.
 * 
 * @author José Alberto Espinoza
 */
public class PBKDF2Encoder {
	@ConfigProperty(name = "demo-crum.quarkusjwt.password.secret")  private String secret;
	@ConfigProperty(name = "demo-crum.quarkusjwt.password.iteration")  private Integer iteration;
	@ConfigProperty(name = "demo-crum.quarkusjwt.password.keylength")  private Integer keylength;
	
	/**
	 * More info <a href="https://www.owasp.org/index.php/Hashing_Java">(https://www.owasp.org/index.php/Hashing_Java)</a> 
	 * @param cs un string con la contraseña sin codificar
	 * @return contraseña codificada
	 * @author José Alberto Espinoza
	 */
	public String encode(CharSequence cs) {
		try {
			byte[] result = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512")
											.generateSecret(new PBEKeySpec(cs.toString().toCharArray(), secret.getBytes(), iteration, keylength))
											.getEncoded();
			return Base64.getEncoder().encodeToString(result);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
			throw new RuntimeException(ex);
		}
	}
}
