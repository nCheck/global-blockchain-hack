pragma solidity ^0.4.17;



contract Donation {

    address owner;
    uint totalSupply;

    enum Status {Accepted, Rejected, Pending }

    struct donation {
        uint time;
        Status status;
    }

    mapping(uint => uint) public rating;
    mapping(uint => address) public ngo;
    mapping(uint => donation) public donationStart;
    mapping(uint => uint) public balances;
    
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
        balances[_id] = 10000;

    }
    
    function getRating (uint ngoId) public view returns (uint) {
        return rating[ngoId];
    }

    function userDonate (uint did) public returns (uint) {
        uint curr = now ;
        donationStart[did].time = curr;
        emit userDonatedAt(curr);
    }
    
    function ngoReact(uint did,  uint ngoId) public returns(uint){
        uint curr = now;
        uint hist = donationStart[did].time;
        
        require(donationStart[did].status == Status.Pending, "Can't react again" );
        require(hist != 0, "negative history");
        
        uint duration = curr - hist;
        
        if (duration < 1 days){
            rating[ngoId] += 1;
        }

        emit ngoReacted(duration);
        return duration;
    }
    
    
    function interact(uint ngoId) public {
        rating[ngoId] += 1;
    }
    
    
    function transanct(int amount, uint ngoId) public{
        
        int update = int(balances[ngoId]) + amount;
        
        require( update > 0 , "Insufficient Fund" );
        
        balances[ngoId] = uint(update);
        
    }
    
    
    function getBalance(uint ngoId) public view returns(uint) {
        
        return balances[ngoId];
    }



    

}