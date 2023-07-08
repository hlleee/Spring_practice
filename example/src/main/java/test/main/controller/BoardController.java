package test.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import test.main.entity.Board;
import test.main.service.BoardService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {
	private final BoardService boardService;
	
	 @PostMapping("/writedo")	//delete,insert,update일 때 postMapping ,,, select는 GetMapping
	    public String writedo(Board board, Model model) {
	        boardService.write(board);
	        
	    }

}
