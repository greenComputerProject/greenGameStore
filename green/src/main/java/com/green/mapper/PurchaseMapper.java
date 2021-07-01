package com.green.mapper;

import java.util.List;

<<<<<<< HEAD
=======
import org.apache.ibatis.annotations.Param;

>>>>>>> 83885cc34a97bd644d770a917854ed94ed2d2895
import com.green.domain.PurchaseListVO;
import com.green.domain.PurchaseVO;

public interface PurchaseMapper {
	
	public void insert(PurchaseVO vo);
	public List<PurchaseListVO> getList(String userid);
	public void delete(Long pno);
<<<<<<< HEAD
	
=======
	public Integer getCount(@Param("userid") String userid, @Param("gno") Long gno);
>>>>>>> 83885cc34a97bd644d770a917854ed94ed2d2895
	
}
