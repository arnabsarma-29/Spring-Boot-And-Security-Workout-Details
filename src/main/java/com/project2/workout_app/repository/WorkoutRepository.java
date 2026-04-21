package com.project2.workout_app.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project2.workout_app.entity.Workout;
@Repository
public interface WorkoutRepository extends JpaRepository <Workout, Integer>
{
	boolean existsByName (String name);
	Optional <Workout> findByName (String name);
}