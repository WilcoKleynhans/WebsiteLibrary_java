package com.sandbox.websitelibrary.WebSiteLibrary.controller;


import com.sandbox.websitelibrary.WebSiteLibrary.model.Website;
import com.sandbox.websitelibrary.WebSiteLibrary.service.WebsiteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
class WebSiteLibraryApplicationTests {

	@Mock
	private WebsiteService websiteService;

	@InjectMocks
	private WebsiteController websiteController;

	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(websiteController).build();
	}

	@Test
	public void testGetAllWebsites() throws Exception {
		// Set up mock data (if necessary)
		when(websiteService.getAllWebsites()).thenReturn(List.of(new Website(1L, "Example Website", "example.com", "comment", 5)));

		mockMvc.perform(get("/api/websites"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value(1L))  // Adjusted for array response
				.andExpect(jsonPath("$[0].description").value("Example Website"))
				.andExpect(jsonPath("$[0].url").value("example.com"))
				.andExpect(jsonPath("$[0].comment").value("comment"))
				.andExpect(jsonPath("$[0].rating").value(5));
	}

	@Test
	public void testGetWebsiteById() throws Exception {
		Website website = new Website(1L, "Example Website", "example.com", "comment", 5); // Assuming the constructor exists
		when(websiteService.getWebsiteById(1L)).thenReturn(Optional.of(website));

		mockMvc.perform(get("/api/websites/1")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1L))
				.andExpect(jsonPath("$.description").value("Example Website"))
				.andExpect(jsonPath("$.url").value("example.com"))
				.andExpect(jsonPath("$.comment").value("comment"))
				.andExpect(jsonPath("$.rating").value(5));
	}

	@Test
	public void testCreateWebsite() throws Exception {
		// Set up mock data
		Website website = new Website(1L, "Example Website", "example.com", "comment", 5);
		when(websiteService.createWebsite(any())).thenReturn(website);

		mockMvc.perform(post("/api/websites")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"url\":\"example.com\", \"name\":\"Example Website\"}"))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").value(1L))
				.andExpect(jsonPath("$.description").value("Example Website"))
				.andExpect(jsonPath("$.url").value("example.com"))
				.andExpect(jsonPath("$.comment").value("comment"))
				.andExpect(jsonPath("$.rating").value(5));
	}

	@Test
	public void testUpdateWebsite() throws Exception {
		// Set up mock data
		Website updatedWebsite = new Website(1L, "Updated Website", "updated.com", "comment", 5);
		when(websiteService.updateWebsite(anyLong(), any())).thenReturn(updatedWebsite);

		mockMvc.perform(put("/api/websites/{id}", 1)
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"url\":\"updated.com\", \"name\":\"Updated Website\"}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1L))
				.andExpect(jsonPath("$.description").value("Updated Website"))
				.andExpect(jsonPath("$.url").value("updated.com"))
				.andExpect(jsonPath("$.comment").value("comment"))
				.andExpect(jsonPath("$.rating").value(5));
	}

	@Test
	public void testDeleteWebsite() throws Exception {
		// Set up mock data
		when(websiteService.deleteWebsite(anyLong())).thenReturn(true);

		mockMvc.perform(delete("/api/websites/{id}", 1))
				.andExpect(status().isNoContent());
	}

	@Test
	public void testDeleteWebsiteNotFound() throws Exception {
		// Set up mock data for website not found
		when(websiteService.deleteWebsite(anyLong())).thenReturn(false);

		mockMvc.perform(delete("/api/websites/{id}", 1))
				.andExpect(status().isNotFound());
	}
}
