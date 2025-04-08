package org.snippet.Util;

import org.snippet.Modal.Dto.SnippetDTO;
import org.snippet.Modal.Snippet;

import java.util.ArrayList;
import java.util.List;

public class SnippetMapper {

    public static SnippetDTO convertToDto(Snippet snippet) {
        SnippetDTO dto = new SnippetDTO();
        dto.setId(snippet.getId());
        dto.setLanguage(snippet.getLanguage());
        dto.setCode(snippet.getCode());
        dto.setOwnerUsername(snippet.getUser().getUsername());
        return dto;
    }

    public static List<SnippetDTO> toDTOList(List<Snippet> snippets) {
        List <SnippetDTO> dtos = new ArrayList<>();
        for (Snippet snippet : snippets) {
            dtos.add(convertToDto(snippet));
        }
        return dtos;
    }
}
