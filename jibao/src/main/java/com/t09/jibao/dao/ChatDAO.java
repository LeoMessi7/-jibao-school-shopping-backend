package com.t09.jibao.dao;

import com.t09.jibao.Vo.ChatUser;
import com.t09.jibao.domain.ChatPK;
import com.t09.jibao.domain.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatDAO extends JpaRepository<Chat, ChatPK> {
    @Query(name = "findAllByBoth", nativeQuery = true,// left join user u2 on c.receiverId = u2.id  where senderId=:sender_id and receiverId=:receiver_id
            value = "select new com.t09.jibao.Vo.ChatUser(u1.name, u1.name, c.content, c.chatTime) from chat c left join user u1 on c.senderId = u1.id")
    List<ChatUser> findAllByBoth(@Param("sender_id") Long sender_id, @Param("receiver_id") Long receiver_id);
}
