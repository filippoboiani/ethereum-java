package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.parity.Parity;
import org.web3j.tx.Contract;

import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;


import java.sql.Timestamp;
import java.util.concurrent.ExecutionException;

/**
 * Created by filippoboiani on 27/05/2017.
 */
@Service
public class FirstService {

    // This is possible because you have placed in your pom.xml the web3j Spring Boot Starter
    // otherwise you would have needed to write
    // Web3j web3 = Web3j.build(new HttpService("http://localhost:8545"));  // defaults to http://localhost:8545/
    @Autowired
    private Web3j web3j;
    @Autowired
    Parity parity;

    public String getClientVersion() {
        Web3ClientVersion web3ClientVersion = null;
        try {
            web3ClientVersion = web3j.web3ClientVersion().send();
            return web3ClientVersion.getWeb3ClientVersion();
        } catch (IOException e) {
            e.printStackTrace();
            return "An error occurred while attempting to connect to Web3j...";
        }

    }
    public Credentials getCredentials() {

        if(GlobalInfo.getInstance().getWalletFile() == null){
            // For now, the wallet should exist...
            this.createWallet();
            this.fillWallet();
        }
        System.out.println(GlobalInfo.getInstance());
        try {
            return WalletUtils.loadCredentials(
                    GlobalInfo.getInstance().getWalletPwd(),
                    GlobalInfo.getInstance().getWalletFile()
            );

        } catch (IOException e) {
            e.printStackTrace();
        } catch (CipherException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Do not use this, the wallet should exist...
    public void createWallet(){
        String fileName = "";
        try {
            // How to create a wallet
            fileName = WalletUtils.generateFullNewWalletFile(
                            GlobalInfo.getInstance().getWalletPwd(),
                            new java.io.File(GlobalInfo.getInstance().getWalletFilePath())
            );
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (CipherException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Fill the wallet in some way...
    // Ideally, the application should have a wallet from the beginning because otherwise we don't know how to fund it.
    public void fillWallet(){
        // todo...
    }

    public String deployContranct()  {
        FirstContract contract = null;
        Long time = 0L;
        try {
            // Start deployment
            Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());

            contract = FirstContract.deploy(web3j, this.getCredentials(), Contract.GAS_PRICE, Contract.GAS_LIMIT, BigInteger.valueOf(0)).get();

            // End deployment
            Timestamp timestamp2 = new Timestamp(System.currentTimeMillis());

            // Set the contract globally
            GlobalInfo.getInstance().setContract(contract);

            // Calculate the time span
            time = (timestamp2.getTime()-timestamp1.getTime())/1000;

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        String ret = "{'deploying_time': "+time+" s, 'address': "+contract.getContractAddress()+"}";
        return ret;
    }

    public void addSocialRecord(String globalID) {
        ((FirstContract) GlobalInfo.getInstance().getContract()).addGlobalId( new Utf8String(globalID), new Utf8String("Hash value f9dgsg8dsg8dsf8gsd8fg8g"));
    }

    public String getSocialRecord(String globalID) {
        String val = null;
        try {
            val = ((FirstContract) GlobalInfo.getInstance().getContract()).getSocialRecordHash(new Utf8String(globalID)).get().getValue();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return val;

    }
}
