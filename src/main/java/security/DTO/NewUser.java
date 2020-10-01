package security.DTO;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewUser {
	private String name;
	private String rfc;
	private String username;
	private String password;
	private Set<String> roles = new HashSet<>();
}
