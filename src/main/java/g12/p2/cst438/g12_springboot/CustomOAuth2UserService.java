package g12.p2.cst438.g12_springboot;

import g12.p2.cst438.g12_springboot.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    /**
     * Constructor for CustomOAuth2UserService.
     * @param userRepository UserRepository for DB queries to check admin privileges and add user in if they don't exist.
     */
    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Overridden loadUser method that will check the DB for the logged-in user, and add them if they do not exist.
     * @param userRequest Request to the userInfo endpoint, used to load in an OAuth2User.
     * @return an OAuth2User with the appropriate privileges for their level of access.
     * @throws OAuth2AuthenticationException Not implemented.
     */
    @Override
    public OAuth2User loadUser(@AuthenticationPrincipal OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String email = oAuth2User.getAttribute("email");
        Optional<User> checkUser = Optional.ofNullable(userRepository.getUserByEmail(email));

        if (checkUser.isEmpty()) {
            String name = oAuth2User.getAttribute("name");
            User newUser = new User(name, email);
            userRepository.insertUser(newUser);
        }

        if (checkUser.isPresent() && checkUser.get().isAdmin()) {
            List<GrantedAuthority> authorities = new ArrayList<>(oAuth2User.getAuthorities());
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

            return new OAuth2User() {
                @Override
                public Map<String, Object> getAttributes() {
                    return oAuth2User.getAttributes();
                }

                @Override
                public Collection<? extends GrantedAuthority> getAuthorities() {
                    return authorities;
                }

                @Override
                public String getName() {
                    return oAuth2User.getName();
                }
            };
        }

        return oAuth2User;
    }
}
