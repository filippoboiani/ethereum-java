package hello;

import org.web3j.tx.Contract;

import java.io.File;
import java.math.BigInteger;

/**
 * Created by filippoboiani on 27/05/2017.
 */
public class GlobalInfo {

    // Global info
    private static GlobalInfo _singleton = null;

    private String walletPwd = null;
    private String walletFilePath = null;
    private String walletFileName = null;
    private final BigInteger GAS_PRICE = null;
    private final BigInteger GAS_LIMIT = null;
    private final BigInteger INITIAL_WEI_VALUE = null;
    private File walletFile = null;
    private Contract contract = null;
    // Private Constructor
    private GlobalInfo()
    {
        setDefaultValues();
    }

    // Instance
    public static GlobalInfo getInstance()
    {
        if(_singleton == null)
        {
            _singleton = new GlobalInfo();
        }
        return _singleton;
    }

    private void setDefaultValues()
    {
        this.walletPwd = "test-account-pwd";
        this.walletFilePath = "/Users/filippoboiani/Library/Ethereum/testnet/keystore/";
        this.walletFileName = "UTC--2017-05-26T17-35-59.543649770Z--9d9e76d28371fdee907b4e8cf3d6a89330df18c5";
        this.walletFile = new File(walletFilePath+walletFileName);
    }

    public String getWalletPwd() {return walletPwd;}

    public String getWalletFilePath() {return walletFilePath;}

    public String getWalletFileName() {return walletFileName;}

    public File getWalletFile() {

        if(!walletFile.exists()){
            // create Wallet and add funds...
            return null;
        } else {
            return walletFile;
        }

    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public String toString(){
        return "{\n\t'wallet-pwd': '" +
                this.walletPwd + "',\n\t'wallet-name': '" +
                this.walletFileName + "',\n\t'wallet-path': '" +
                this.walletFilePath + "',\n\t'fileExists': "+ this.walletFile.exists()+"\n}\n";
    }
}
