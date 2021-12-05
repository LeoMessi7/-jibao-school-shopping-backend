package com.t09.jibao.service;
import com.t09.jibao.domain.Upload;
import com.t09.jibao.domain.Withdraw;

public interface WithdrawService {
    Withdraw save(Withdraw withdraw);

    Withdraw findById(Long id);

    Withdraw findByGid(Long gid);
}
