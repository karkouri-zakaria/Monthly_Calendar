package org.daypilot.demo.html5eventcalendarspring.repositories;

import org.daypilot.demo.html5eventcalendarspring.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
	@Query("from Event e where not(e.end < :from or e.start > :to)")
	List<Event> findBetween(
			@Param("from") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime start,
			@Param("to") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime end
	);

	List<Event> findByTextContainingIgnoreCase(String text);

	List<Event> findByColor(String color);

	List<Event> findByStartGreaterThanEqualAndEndLessThanEqual(
			@Param("start") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime start,
			@Param("end") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime end
	);

	@Query("from Event e where e.start >= :start and e.end <= :end and e.color = :color")
	List<Event> findByDateRangeAndColor(
			@Param("start") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime start,
			@Param("end") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime end,
			@Param("color") String color
	);
}
