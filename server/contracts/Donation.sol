pragma solidity ^0.4.17;



contract Donation {

    address owner;
    uint totalSupply;
    mapping(address => uint) public rating;
    mapping(uint => address) public ngo;
    mapping(uint => uint) public donationStart;
    mapping(address => uint) public balances;
    
    event userDonatedAt(
        uint id
        );

    constructor () public {

        owner = msg.sender;
        totalSupply = 100000;
    }

    function addNgo (uint _id, address add) public  {
        //require(add == owner);
        ngo[_id] = add;
        rating[add] = 0;

    }
    
    function getRating (uint _id) public view returns (uint) {
        address nad = ngo[_id];
        return rating[nad];
    }

    function userDonate (uint _id) public returns (uint) {
        uint curr = now ;
        donationStart[_id] = curr;
        return curr;
    }
    
    function ngoReact(uint _id,  uint ngoId) public {
        uint curr = now;
        uint hist = donationStart[_id];
        
        require(hist != 0, "negative history");
        
        uint duration = curr - hist;
        
        if (duration < 1 days){
            address ngoAdd = ngo[ngoId];
            rating[ngoAdd] += 1;
        }
    }

    

}