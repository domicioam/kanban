package com.dgsystems.kanban.usecases;

import com.dgsystems.kanban.entities.Board;
import com.dgsystems.kanban.entities.CardList;

import java.util.Optional;

public class AddCardListToBoard {
    private final BoardRepository boardRepository;

    public AddCardListToBoard(BoardRepository repository) {
        boardRepository = repository;
    }

    public void execute(String boardName, String cardListTitle) {
        Optional<Board> optional = boardRepository.getBoard(boardName);
        optional.map(b -> {
            Board updated = b.addCardList(new CardList(cardListTitle));
            boardRepository.save(updated);
            return updated;
        }).orElseThrow();
    }
}