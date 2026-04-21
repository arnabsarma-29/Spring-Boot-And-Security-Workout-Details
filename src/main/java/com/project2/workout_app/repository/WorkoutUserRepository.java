package com.project2.workout_app.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.project2.workout_app.entity.WorkoutUser;
@Repository
public interface WorkoutUserRepository extends JpaRepository <WorkoutUser, Integer>
{
	boolean existsByUsername (String username);
	WorkoutUser findByUsername (String username);
	@Modifying
	@Query ("UPDATE WorkoutUser u SET u.username = :newUsername WHERE u.username = :oldUsername")
	void updateUsername (@Param ("oldUsername") String oldUsername, @Param ("newUsername") String newUsername);
}