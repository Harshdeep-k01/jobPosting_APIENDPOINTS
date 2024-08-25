package com.example.crezan.Service;

import com.example.crezan.Entity.JobPosting;
import com.example.crezan.Repository.JobPostingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JobPostingServiceTest {

    @Mock
    private JobPostingRepository repository;

    @InjectMocks
    private JobPostingService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initializes mocks
    }

    @Test
    void testGetAllJobPostings() {
        // Mock data for testing
        List<JobPosting> mockPostings = Arrays.asList(new JobPosting(), new JobPosting());
        when(repository.findAll()).thenReturn(mockPostings); // Mock findAll method

        // Call the service method
        List<JobPosting> result = service.getAllJobPostings();

        // Validate the results
        assertEquals(2, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetJobPostingById() {
        // Mock data for testing
        JobPosting mockPosting = new JobPosting();
        when(repository.findById(1L)).thenReturn(Optional.of(mockPosting)); // Mock findById method

        // Call the service method
        Optional<JobPosting> result = service.getJobPostingById(1L);

        // Validate the results
        assertTrue(result.isPresent());
        verify(repository, times(1)).findById(1L);
    }

    // Add more tests for other methods if necessary
}
