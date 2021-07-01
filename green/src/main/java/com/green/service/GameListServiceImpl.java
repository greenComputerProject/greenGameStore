package com.green.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.domain.GameIndexVO;
import com.green.domain.GameListVO;
import com.green.mapper.GameListMapper;
import com.green.utils.Criteria;
import com.green.utils.GameListFunctional;
import com.green.utils.SortFilterSearch;

@Service
public class GameListServiceImpl implements GameListService {
	
	@Autowired
	private GameListMapper mapper;

	private GameListFunctional func;
	
	@Override
	public List<GameListVO> getList(Criteria cri , SortFilterSearch sort) {
<<<<<<< HEAD
		func = new GameListFunctional() {
			
			@Override
			public Object method(Criteria cri, SortFilterSearch sort) {
				return mapper.getList(cri, sort);
			}
		};
=======
		
		func = (x, y) -> {return mapper.getList(x, y);};
>>>>>>> 83885cc34a97bd644d770a917854ed94ed2d2895
		
		return (List<GameListVO>) checkCondition(func, cri, sort);
	}

	@Override
	public List<GameIndexVO> getListForIndex() {
		// TODO Auto-generated method stub
		return mapper.getListForIndex();
	}

	@Override
	public Integer getCount(Criteria cri, SortFilterSearch sort) {
		// TODO Auto-generated method stub
<<<<<<< HEAD
		func = new GameListFunctional() {
			@Override
			public Object method(Criteria cri, SortFilterSearch sort) {
				return mapper.getCount(cri, sort);
			}
		};
=======
		func = (x, y) -> {return mapper.getCount(x, y);};
>>>>>>> 83885cc34a97bd644d770a917854ed94ed2d2895
		
		return (Integer) checkCondition(func, cri, sort);
	}
	
	public Object checkCondition(GameListFunctional func, Criteria cri, SortFilterSearch sort){
		
		if(cri != null && sort != null) return func.method(cri, sort);
		else if(cri != null && sort == null) return func.method(cri, null);
		else if(cri == null && sort != null) return func.method(new Criteria(), sort);
		else return func.method(new Criteria(), null);
	}

}


