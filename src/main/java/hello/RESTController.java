package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.web3j.crypto.CipherException;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.concurrent.ExecutionException;

@RestController
public class RESTController {

    // Dependendy injection, i declare the object I need and it will be instantiated only when I use it.
    @Autowired
    FirstService firstService;

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!\n";
    }

    @RequestMapping("/test")
    public String getAaa()  throws IOException, ExecutionException, InterruptedException, CipherException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException {

        return "The route seems to be working...\n";
    }

    @RequestMapping("/client/version")
    public String getClientVersion() {
        // This is the first time i use firstService, the instance is here automatically created.
        return firstService.getClientVersion()+"\n";
    }

    @RequestMapping("/deploy/contract")
    public String deployContract() {
        // This is the first time i use firstService, the instance is here automatically created.
        return firstService.deployContranct()+"\n";
    }

    @RequestMapping("/add/socialrecord/{globalID}")
    public String addSocialRecord(@PathVariable("globalID") String globalID) {
        firstService.addSocialRecord(globalID);
        return "Social record with globalID: "+globalID+" added\n";
    }

    @RequestMapping("/socialrecord/{globalID}")
    public String getSocialRecord(@PathVariable("globalID") String globalID) {
        String res = firstService.getSocialRecord(globalID);
        System.out.println(res);
        if(res.length()<3)
            return "The transaction still needs some time to be mined...\n";
        else
            return res+"\n";
    }



}
