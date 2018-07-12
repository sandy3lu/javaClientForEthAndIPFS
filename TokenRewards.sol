pragma solidity ^0.4.21;

contract TokenRewards {
    address public owner;
    uint256 public limit;
    uint256 public rewards;
    address public contractAddress = this;

    constructor() public {
        owner = msg.sender;
        limit = 1;
        rewards = 1;
    }

    modifier onlyOwner() {
        require(msg.sender == owner);
        _;
    }

    function () public payable {}

    function transferOwnership(address _newOwner) public onlyOwner {
        require(_newOwner != address(0));
        owner = _newOwner;
    }

    function setLimit(uint256 _limit) public onlyOwner {
        limit = _limit;
    }
    
    function setRewards(uint256 _rewards) public onlyOwner {
        rewards = _rewards;
    }

    function contractBalance() public view returns (uint256) {
        return contractAddress.balance;
    }

    function contractWithdraw() public onlyOwner {
        owner.transfer(contractAddress.balance);
    }

    function tokenRewards() public {
        require(msg.sender.balance < limit * 1 ether);
        msg.sender.transfer(rewards * 1 ether);
    }
}