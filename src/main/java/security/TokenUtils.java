package security;

import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

import org.jboss.logging.Logger;

import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.build.JwtClaimsBuilder;
import model.Role;


/**
 * Encargada de la generación del token de acceso que se le proporcionara a cualquier usuario con credenciales validas
 * de acceso, construido con la información necesaria para la aplicación cliente como nombre del sujeto, grupos o roles
 * expiración del token e issuer.
 * 
 * @author José Alberto Espinoza
 * @version %I%, %G%
 */
public class TokenUtils {
	private static final Logger log = Logger.getLogger(TokenUtils.class);
	/**
	 * Metodo encargado de generar el token de acceso que sera enviado a los usuarios que inicien sesión con credenciales validad.
	 * @author José Alberto Espinoza
	 * @param username el nombre del usuario utilizado para construir el token de acceso
	 * @param roles los roles que seran asignados al usuario que reciba el token
	 * @param duration la duración del token en segundos
	 * @param issuer
	 * 
	 * @return el token generado con los parametros introducidos a la funcion
	 */

	public static String generateToken(String username, Set<Role> roles, Long duration, String issuer) throws Exception {
		String privateKeyLocation = "/privatekey.pem";
		PrivateKey privateKey =  readPrivateKey(privateKeyLocation);
		JwtClaimsBuilder claimsBuilder = Jwt.claims();
		long currentTimeInSecs = currentTimeInSecs();
		
		Set<String> groups = new HashSet<>();
		for (Role role : roles) {
			groups.add(role.getRole().toString());
		}
		claimsBuilder.issuer(issuer);
		claimsBuilder.subject(username);
		claimsBuilder.issuedAt(currentTimeInSecs);
		claimsBuilder.expiresAt(currentTimeInSecs + duration);
		claimsBuilder.groups(groups);

		return claimsBuilder.jws().keyId(privateKeyLocation).sign(privateKey);
	}

	/**
	 * Es un metodo auxiliar a la funcion principal de la clase <code>generateToken</code> que permite leer la llave privada
	 * para posteriormente procesarla en la funcion <code>decodePrivateKey</code> y retornar la llave decodificada
	 * @param pemResName la dirección relativa o absulota de la llave privada
	 * @return la llave de la dirección proporcionada decodificada
	 * @throws Exception
	 */
	public static PrivateKey readPrivateKey(final String pemResName) throws Exception {
		try (InputStream contentIS = TokenUtils.class.getResourceAsStream(pemResName)) {
			byte[] tmp = new byte[4096];
			int length = contentIS.read(tmp);
			return decodePrivateKey(new String(tmp, 0, length, "UTF-8"));
		}
	}

	/**
	 * Es un metodo auxiliar que recibe la llave privada para su decodificacion, se decodifica en base 64 y con
	 * los bytes obtenidos se pasa a <code>KeyFactory</code> para la generación de la llave.
	 * 
	 * @param pemEncoded string con la llave privada codificada
	 * @return la llave privada generada con los byt
	 * @throws Exception
	 */
	public static PrivateKey decodePrivateKey(final String pemEncoded) throws Exception {
		byte[] encodedBytes = toEncodedBytes(pemEncoded);

		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encodedBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePrivate(keySpec);
	}
	/**
	 * Recibe la llave privada y devuelve un arreglo de bytes
	 * @return un arreglo de bytes recopilados de la llave privada
	 */
   public static byte[] toEncodedBytes(final String pemEncoded) {
		final String normalizedPem = removeBeginEnd(pemEncoded);
		return Base64.getDecoder().decode(normalizedPem);
	}

	/**
	 * Recibe la llave privada decodificada y elimina algunas lineas innecesarias del mismo.
	 * @param pem un string con la llave decodificada
	 * @return la llave decodificada sin las lineas irrelevantes
	 */
	public static String removeBeginEnd(String pem) {
		log.info(pem);
		pem = pem.replaceAll("-----BEGIN (.*)-----", "");
		pem = pem.replaceAll("-----END (.*)----", "");
		pem = pem.replaceAll("\r\n", "");
		pem = pem.replaceAll("\n", "");
		return pem.trim();
	}

	/**
	 * Funcion que retorna el tiempo actual en milisegundos
	 * @return tiempo actual en milisegundos
	 */
	public static int currentTimeInSecs() {
		long currentTimeMS = System.currentTimeMillis();
		return (int) (currentTimeMS / 1000);
	}
		
}
