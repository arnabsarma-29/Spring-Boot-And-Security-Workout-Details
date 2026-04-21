package com.project2.workout_app.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project2.workout_app.entity.Goal;
import com.project2.workout_app.entity.WorkoutUser;
@Repository
public interface GoalRepository extends JpaRepository <Goal, Integer>
{
	Optional <Goal> findByName (String name);
	boolean existsByNameAndWorkoutUser (String name, WorkoutUser user);
}