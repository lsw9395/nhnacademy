package com.nhnacademy.todo.mapper;

import com.nhnacademy.todo.domain.Event;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface EventMapper {
    void save(Event event);
    //수정
    void update(Event event);

    //삭제
    void deleteById(Map<String, Object> map);

    //조회
    Event getEvent(long eventId);

    //일단위 조회
    List<Event> findAllByDay(Map<String, Object> map);

    //월단위 조회
    List<Event> findAllByMonth(Map<String, Object> map);

    //일별 등록 카운트
    long countByUserIdAndEventAt(Map<String, Object> map);

    //일별 삭제
    void deletebyDaily(Map<String, Object> map);


}
