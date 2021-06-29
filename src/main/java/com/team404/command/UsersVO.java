package com.team404.command;



import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //toString, getter, setter 자동생성
@AllArgsConstructor //모든멤버변수 초기화생성
@NoArgsConstructor //기본생성자
public class UsersVO {
	
    private String userID;
    private String userPW ;
    private String userName;
    
    private String userEmail1 ;
    private String userEmail2 ;
    
    private String addrZipNum;
    private String addrBasic ;
    private String addrDetail;
    private Timestamp regdate;
}
