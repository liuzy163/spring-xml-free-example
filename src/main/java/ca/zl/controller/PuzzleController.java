package ca.zl.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ca.zl.domain.DataBean;
import ca.zl.domain.ProblemDataBean;
import ca.zl.exception.UnsupportedComplexityException;
import ca.zl.logging.SimpleMethodLogging;
import ca.zl.service.ProblemSolvingService;

@Controller
public class PuzzleController {
	private final ProblemSolvingService problemSolvingService;

	@Autowired
	public PuzzleController(ProblemSolvingService problemSolvingService) {
		this.problemSolvingService = problemSolvingService;
	}

	@RequestMapping(value = "/start", method = RequestMethod.GET)
	@SimpleMethodLogging(beforeMessage = "Initializing", afterMessage = "New view point returned")
	public ModelAndView getEmptyDataBean() {
		ModelAndView modelAndView = new ModelAndView("puzzle", "command", new ProblemDataBean());
		return modelAndView;
	}

	@RequestMapping(value = "/solution", method = RequestMethod.POST)
	@ResponseBody
	@SimpleMethodLogging
	public DataBean solvePuzzle(@Valid @RequestBody ProblemDataBean dataBean)
			throws UnsupportedComplexityException {
		return problemSolvingService.solve(dataBean);
	}
}
