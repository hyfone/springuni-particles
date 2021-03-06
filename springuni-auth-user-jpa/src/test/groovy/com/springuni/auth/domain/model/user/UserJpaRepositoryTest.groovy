package com.springuni.auth.domain.model.user

import com.springuni.auth.domain.model.AuthJpaRepositoryTestConfiguration
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.transaction.annotation.Transactional

import static org.junit.Assert.*

/**
 * Created by lcsontos on 5/5/17.
 */
@RunWith(SpringJUnit4ClassRunner)
@ContextConfiguration(classes = [AuthJpaRepositoryTestConfiguration])
@Transactional
@Rollback
class UserJpaRepositoryTest {

  @Autowired
  UserRepository userRepository

  User user

  @Before
  void before() {
    user = new User(1, "test", "test@springuni.com")
    user.addConfirmationToken(ConfirmationTokenType.EMAIL, 10)
    userRepository.save(user)
  }

  @Test
  void testDelete() {
    userRepository.delete(user.id)
    Optional<User> userOptional = userRepository.findById(user.id)
    assertFalse(userOptional.isPresent())
  }

  @Test
  void testFindById() {
    Optional<User> userOptional = userRepository.findById(user.id)
    assertTrue(userOptional.isPresent())
  }

  @Test
  void testFindByEmail() {
    Optional<User> userOptional = userRepository.findByEmail(user.email)
    assertTrue(userOptional.isPresent())
  }

  @Test
  void testFindByScreenName() {
    Optional<User> userOptional = userRepository.findByScreenName(user.screenName)
    assertTrue(userOptional.isPresent())
  }

}
