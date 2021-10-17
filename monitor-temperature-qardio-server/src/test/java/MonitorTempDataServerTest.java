import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc

class MonitorTempDataServerTest {
  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper mapper;


  @Test
  @DisplayName("When all hotels are requested then they are all returned")
  void saveTempData() throws Exception {
    mockMvc
            .perform(post("/temperatureData/saveTempData"))
            .andExpect(status().is2xxSuccessful());

  }


  @Test
  void getAggregatedData() throws Exception {
    mockMvc
            .perform(get("/temperatureData/getAggregatedTempData"))
            .andExpect(status().is2xxSuccessful())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"));

  }

}