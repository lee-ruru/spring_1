package com.ict.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ict.domain.ReplyVO;
import com.ict.mapper.BoardMapper;
import com.ict.mapper.ReplyMapper;
@Service
public class ReplyServiceImpl implements ReplyService {

	// 서비스는 매퍼를 호출하기때문에 매퍼를 위에 선언해야 함
	@Autowired
	private ReplyMapper mapper;
	
	// 0502댓글쓰기시 Board_tbl쪽에도 관여해야 하믏 board테이블을 수정하는 Mapper를 추가선언합니다. 
	@Autowired
	private BoardMapper boardmapper;
	
	@Transactional // 2개이상의 db접근 군문이 사용되면 트랜잭션 적용
	@Override
	public void addReply(ReplyVO vo) {
		mapper.create(vo);
		// 댓글 번호는 ReplyVO에 들어있으므로 getter를 활용
		boardmapper.updateReplyCount(vo.getBno(), 1);
	}
	
	@Override
	public List<ReplyVO> listReply(Long bno){
		return mapper.getList(bno);
	}
	
	@Override
	public void modifyReply(ReplyVO vo) {
		mapper.update(vo);
	}
	
	@Transactional
	@Override
	public void removeReply(Long rno) {
		// 글 삭제 전에 먼저 bno번을 채취해놓고
		Long bno = mapper.getBno(rno);
		// 다음 글 삭제해야 문제 없이 글 번호를 가져옵니다. 
		mapper.delete(rno);	
		// db에서 커밋 안하면 pending 상태로 계속 지연되니 주의
		boardmapper.updateReplyCount(bno, -1);
	
	}
	
		
	
	
	
	
	
}