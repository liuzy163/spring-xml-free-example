package ca.zl.controller;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import ca.zl.domain.DataBean;
import ca.zl.domain.ProblemDataBean;
import ca.zl.exception.UnsupportedComplexityException;
import ca.zl.service.ProblemSolvingService;

public class PuzzleControllerTest {

	@Test
	public void testGetEmptyDataBean() {
		PuzzleController controller = new PuzzleController(null);
		ModelAndView modelAndView = controller.getEmptyDataBean();
		Assert.assertEquals("puzzle", modelAndView.getViewName());
	}

	@Test
	public void testSolvePuzzle() throws UnsupportedComplexityException {
		ProblemDataBean problem = new ProblemDataBean("1;2;4;3", 2, "a");
		DataBean expectedAnswer = new DataBean("1;2;3;4", 3);

		ProblemSolvingService service = EasyMock
				.createMock(ProblemSolvingService.class);
		EasyMock.expect(service.solve(problem)).andReturn(expectedAnswer);
		EasyMock.replay(service);

		PuzzleController controller = new PuzzleController(service);

		Assert.assertEquals(expectedAnswer, controller.solvePuzzle(problem));
	}
}
