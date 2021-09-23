package br.com.zup.vivo.easy.da.application.googleplay.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.client.auth.oauth2.Credential;

import br.com.zup.vivo.easy.da.domain.google.auth.GoogleAuthService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/google-play")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GooglePlayController {

    @Autowired
    private GoogleAuthService auth;

    @GetMapping("/test")
    public String test(
            @RequestParam(value = "id", required = true) String id,
            @RequestParam(value = "secret", required = true) String secret){

        return "";

       /* try {
            Credential credentials = auth.getCredentials(id, secret);

            String teste = "";
        } catch (IOException e) {
            e.printStackTrace();
        }

        return auth.authorize("http://localhost:8080", id, secret);*/
    }

}
