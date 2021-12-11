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
    @Query(value = "select new com.t09.jibao.Vo.ChatUser(u1.name, u2.name, c.content, c.pk.chatTime) from chat c left join user u1 on c.pk.sender.id = u1.id left join user u2 on c.pk.receiver.id = u2.id  where c.pk.sender.id=:sender_id and c.pk.receiver.id=:receiver_id")
    List<ChatUser> findAllByBoth(@Param("sender_id") Long sender_id, @Param("receiver_id") Long receiver_id);
}
