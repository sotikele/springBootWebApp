package com.example.check.mappers;

import com.example.check.dtos.BookDTO;
import com.example.check.models.Book;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookMapper {

    Book bookDTOToBook(BookDTO bookDTO);
    BookDTO bookToBoookDTO(Book book);
}
