pragma solidity ^0.4.17;



contract Donation {

    address owner;
    uint totalSupply;
    mapping(uint => uint) public rating;
    mapping(uint => address) public ngo;
    mapping(uint => uint) public donationStart;
    //mapping(address => uint) public balances;
    
    event userDonatedAt(
        uint id
        );
    
    event ngoReacted(uint id);

    constructor () public {

        owner = msg.sender;
        totalSupply = 100000;
    }

    function addNgo (uint _id, address add) public  {
        require(msg.sender == owner, "Sender is not owner");
        ngo[_id] = add;
        rating[_id] = 0;

    }
    
    function getRating (uint ngoId) public view returns (uint) {
        return rating[ngoId];
    }

    function userDonate (uint did) public returns (uint) {
        uint curr = now ;
        donationStart[did] = curr;
        emit userDonatedAt(curr);
    }
    
    function ngoReact(uint did,  uint ngoId) public returns(uint){
        uint curr = now;
        uint hist = donationStart[did];
        
        require(hist != 0, "negative history");
        
        uint duration = curr - hist;
        
        if (duration < 1 days){
            rating[ngoId] += 1;
        }

        emit ngoReacted(duration);
        return duration;
    }

    

}