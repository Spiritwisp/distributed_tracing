package com.godel.presentation.tracing.repository;

import com.godel.presentation.tracing.entity.User;
import com.godel.presentation.tracing.utils.HibernateUtil;
import org.hibernate.Session;

public class UserRepository {

  private final Session session = HibernateUtil.getSessionFactory().openSession();

  public String getAddressByUserId(Integer id) {
    User user = session.get(User.class, id);
    return user.getAddress();
  }
}
