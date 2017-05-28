# Trials...

- This is a Spring Boot Project
- Import it into IntelliJ using pom.xml file


## How to do everything 

### Step 1 
- Use the following command to compile the contract

    ```
    solcjs FirstContract.sol --bin --abi --optimize -o ./ 
  ```
- If you don't have solcjs you need to 
    ```
    npm install -g solc
  ```
- If you don't have npm, you are not a programmer. 

### Step 2 
- Go to the /resource folder 
- Write this in you terminal

  ```
  web3j solidity generate FirstContract.bin FirstContract.abi -o ../java -p hello
  ```
- The command could not work if solcjs compiles the file in this way: 

     ```javascript
     FirstContranct.sol:FirstContract.bin
    FirstContranct.sol:FirstContract.abi
    ```
- Then you need only to rename the files deleting the first part
- Now you have both the Solidity contract compiled and the Web3j wrapper class

### Step 2b
- In order to deploy contract you need a wallet
- Create one with Misc or Ethereum Wallet 
- Change the file GlobalInfo.java 
- Change the walletFilePath, walletFileName, walletPwd

 ```javascript
    private void setDefaultValues()
     {
         this.walletPwd = "YourPassword";
         this.walletFilePath = "/Users/your/path/to/Library/Ethereum/testnet/keystore/";
         this.walletFileName = "UTC--2017-05-26T17-35-59.543649770Z....bla bla";
     }
  ```    
### Step 3 
- In order to connect to a client you need to start one. I do prefer geth but you can use Parity or Infuria.
- To start geth: 

    `geth --fast --cache=512 --rpcapi personal,db,eth,net,web3 --rpc --testnet`
    
### Step 4 
- Start the application 
- Call the routes either with a browser or curl 

    `curl localhost:8080/test
    curl localhost:8080/client/version
    curl localhost:8080/deploy/contract`