pragma solidity ^0.4.0;
contract FirstContract {
    string public message;

    mapping (address => string) users;

    mapping (string => string) globalIds;

    function FirstContract(){
        message = "Beautiful Contract";
        users[msg.sender] = "Wazzaaaaap??";
    }

    function addUser(address userAddress, string name) returns (string){
        users[userAddress] = name;
    }


    function addGlobalId(string globalId, string socialRecordHash) returns (string){

        globalIds[globalId] = socialRecordHash;

    }

    function getSocialRecordHash(string globalId) constant returns (string socialRecordHash){

        socialRecordHash = globalIds[globalId];
        return socialRecordHash;
    }

    function updateSocialRecordHash(string globalId, string socialRecordHash) returns (string){
        globalIds[globalId] = socialRecordHash;
    }


    function setMessage(string theMessage) returns (string) {
        message = theMessage;
    }

    function getUser(address _user) constant returns (string _theUser){
        _theUser = users[_user];
        return _theUser;
    }
}