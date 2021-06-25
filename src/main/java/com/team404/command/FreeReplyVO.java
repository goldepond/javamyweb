package com.team404.command;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FreeReplyVO {
	private int rno;
	private int bno;
	private String reply;
	private String replyID;
	private String replyPW;
	private Timestamp replyDate;
	private Timestamp updateDate;
}
