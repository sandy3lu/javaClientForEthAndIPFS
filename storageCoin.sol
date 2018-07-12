pragma solidity ^0.4.23;
contract StorageCoin {

    address contractOwner;
    uint256 uploadReward;   
    uint256 saveFileReward; 
    uint256 sharedReward;   

    event uploadInfo(string fileName ,address owner, uint256 fileSizeInM, uint256 copyNum, uint256 months);
    event reNewFileInfo(string fileName ,address owner, uint256 months);
    //event storeInfo(string fileName, address miner, uint256 copyindex);    //miner watch this event to mine
    event downloadInfo(string fileName, address buyer);
    event deleteInfo(string fileName, address operator);
    event proveInfo(uint256 countNum, bool hashresult, address addressCal);


    mapping(string=>fileInfo) files; // file' miner


    struct fileInfo{
        address owner ;
        uint256 copysNum ;
        uint256 ValideDate ;
        uint256 fileSize;
    }
    
    modifier onlyOwner {
        require(msg.sender == contractOwner);
        _;
    }

    function () payable public {}

    function StorageCoin() public payable{
        contractOwner = msg.sender;
        uploadReward = 1 * 10 ** 18;    // 1 eth
        saveFileReward = 1 * 10 ** 18;  // 1 eth
        sharedReward = 5 * 10 ** 17;    // 0.5 eth
    }

    function setUploadReward(uint256 _reward) public onlyOwner {
        uploadReward = _reward;
    }
    
    function setSaveFileReward(uint256 _reward) public onlyOwner {
        saveFileReward = _reward;
    }
    
    function setSharedReward(uint256 _reward) public onlyOwner {
        sharedReward = _reward;
    }
    
    // size in M
    function estimateCost(uint256 size) internal pure returns (uint256 cost){
        if(size>2048) {
			 return 20;
		 }
		 if(size>1024) {
			 return 10;
		 }
		 if(size>500) {
			 return 5;
		 }
		 if(size>200) {
			 return 2;
		 }
		 return 1;
    }
    
    function getFundBack() public onlyOwner{
        address addr = contractOwner;
        addr.transfer(address(this).balance);
    }
    
    // 
    function uploadFile(string fileName, uint256 copyNum, uint256 fileSizeInM, uint256 months) public payable {
         require(copyNum >= 1);
         require(copyNum < 11);
         require(months < 13);
        // 上传文件需要扣除 1eth
        uint256 needLeast = uploadReward * copyNum * estimateCost(fileSizeInM) * months;
        require(msg.value >= needLeast);
        // already uploaded fileName
        require(files[fileName].owner== 0);
        
        // 扣除的 eth 转移到本合约
        address addr = this;
        files[fileName].owner = msg.sender;  // save file's owner
        files[fileName].copysNum = copyNum;  // save file's count
        files[fileName].ValideDate = now + months * 30 * 1 days;  // save file's valid time
        files[fileName].fileSize = fileSizeInM;
        addr.transfer(needLeast);
        msg.sender.transfer(msg.value - needLeast);
        emit uploadInfo(fileName, msg.sender, fileSizeInM, copyNum, months);  // broadcast event
       
    }

    function reNewFileDate(string fileName,uint256 months) public payable returns(uint subValue){
        // already uploaded fileName
        require(files[fileName].owner != address(0));
        require(months >= 1);
        uint256 copyNum = files[fileName].copysNum;   //  file's count
        // 上传文件需要扣除 1eth
        uint256 needLeast = uploadReward * copyNum * months;
        require(msg.value >= needLeast);
 
        
        // 扣除的 eth 转移到本合约
        address addr = this;
        files[fileName].ValideDate = files[fileName].ValideDate + months * 30 * 1 days;  // save file's valid time
        addr.transfer(needLeast);
        msg.sender.transfer(msg.value - needLeast);
        emit reNewFileInfo(fileName, msg.sender, months);  // broadcast event
        return needLeast;
    }
    
    
    function proveStorage(string _count, string _hash, uint8 v, string r, string s) internal  returns (uint countProve) {
        //
        countProve = 0;

        address tee = getSender(_hash, v, r, s);
       // if (tee == msg.sender){
           bool result = compareHash( _count,  _hash);

            if(result){
                bytes memory countNum = hexStr2bytes(_count);
                for(uint i=0;i<countNum.length;i++) {
                    
                    countProve = countProve<< 8;
                    countProve = countProve + uint(countNum[i]);
                }
            }
        //}
        emit proveInfo(countProve, result,  tee);
    }

    function verifyStorageContinue(string _count, string hash, uint8 v, string r, string s) public returns (uint addValue){
        uint countprove = proveStorage(_count,hash, v, r, s);   // verify prove
        require(countprove != 0);
        address contracAdress = this;
        require(contracAdress.balance >= saveFileReward);
        msg.sender.transfer(saveFileReward);    // 奖励
        return saveFileReward;
    }

/*
    function downloadFile(string fileName) public payable {
        address fileowner= files[fileName].owner;
        require(fileowner != address(0)); // 保证文件存在
        require(msg.value >= sharedReward);
        fileowner.transfer(sharedReward);
        msg.sender.transfer(msg.value - saveFileReward);    // 
        emit downloadInfo( fileName, msg.sender);
    }
*/

    function checkOwner(string fileName) public view returns(uint256 ){
        address fileowner= files[fileName].owner;
        if (fileowner == msg.sender) {
            return 1;
        }else {
            return 2;
        }
    }
    
    function deleteFileByOwner(string fileName) public {
        address fileowner= files[fileName].owner;
        require(fileowner != address(0)); // 保证文件存在
        require(fileowner == msg.sender); // only owner can delete 
        files[fileName].owner = 0;
        emit deleteInfo( fileName, msg.sender);
    }
    
    function deleteFile(string fileName) public onlyOwner{
        address fileowner= files[fileName].owner;
        require(fileowner != address(0)); // 保证文件存在
       
        files[fileName].owner = 0;
        emit deleteInfo( fileName, msg.sender);
    }
    
    /*
    function getBanance(address _address) public view returns (uint256){
        return _address.balance;
    }
*/
    function getDate(string fileHash) public view returns (uint256){
        return files[fileHash].ValideDate;
    }
    
    function compareHash(string _count, string _hash) internal  pure returns (bool){
        bytes32 ss =keccak256(_count);
        bytes32 hash = bytesToByte32(hexStr2bytes(_hash));
       
        if(ss == hash)
        {
            return true;
        }
        return false;
    }

    function getSender(string _hash, uint8 v, string r,string s ) internal pure returns (address){
        bytes32 hash1 = bytesToByte32(hexStr2bytes(_hash));
        bytes32 r1= bytesToByte32(hexStr2bytes(r));
        bytes32 s1= bytesToByte32(hexStr2bytes(s));
        address tee= ecrecover(hash1,  v,  r1,  s1);
        // proveInfo( v, r1, s1, tee);
        return tee;
    }

     // 十六进制字符串转换成bytes
    function hexStr2bytes(string data) internal pure returns (bytes){

        //uint _ascii_0 = 48;
        //uint _ascii_A = 65;
        //uint _ascii_a = 97;

        bytes memory a = bytes(data);
        uint[] memory b = new uint[](a.length);

        for (uint i = 0; i < a.length; i++) {
            uint _a = uint(a[i]);

            if (_a > 96) {
                b[i] = _a - 97 + 10;
            }
            else if (_a > 66) {
                b[i] = _a - 65 + 10;
            }
            else {
                b[i] = _a - 48;
            }
        }

        bytes memory c = new bytes((b.length+1) / 2); /// take care of odd length
        uint startindex = 0;
        if((b.length & 0x01)==1){
            c[0] = byte(b[0]);
            startindex = 1;
        }
        for (uint _i = startindex; _i < b.length; _i += 2) {
            c[(_i / 2) + startindex] = byte(b[_i] * 16 + b[_i + 1]);
        }
        return c;
    }

    function stringTobytes(string s) internal pure returns(uint length, bytes array){
        array = bytes(s);
        return (array.length, array);
    }

    function bytesToByte32(bytes memory source) internal pure returns(bytes32 result){
        require(source.length==32);
        assembly{
            result:=mload(add(source,32))
        }
    }
}

