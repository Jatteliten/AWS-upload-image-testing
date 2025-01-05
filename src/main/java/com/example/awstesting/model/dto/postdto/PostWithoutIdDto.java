package com.example.awstesting.model.dto.postdto;

import com.example.awstesting.model.ImageLink;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostWithoutIdDto {
    String text;
    LocalDate date;
    @OneToOne
    ImageLink imageLink;
}
