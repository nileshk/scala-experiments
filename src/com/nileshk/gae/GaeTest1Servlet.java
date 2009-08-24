package com.nileshk.gae;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class GaeTest1Servlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String question = req.getParameter("q");
		String answer = req.getParameter("a");

		PersistenceManager pm = PMF.get().getPersistenceManager();

		if (question != null && answer != null && question.length() > 0 && answer.length() > 0) {

			FlashCard card = new FlashCard(question, answer);
			try {
				pm.makePersistent(card);
				resp.setContentType("text/plain");
				resp.getWriter().println(
						"Saved: " + card.getId().toString() + " question: " + card.getQuestion()
								+ " answer: " + card.getAnswer());
			} finally {
				pm.close();
			}
		} else {
			resp.setContentType("text/plain");
			Query query = pm.newQuery(FlashCard.class);
			query.setOrdering("question desc");
			try {
				@SuppressWarnings("unchecked")
				List<FlashCard> results = (List<FlashCard>) query.execute();
				if (results.iterator().hasNext()) {
					for (FlashCard card : results) {
						resp.getWriter().println(
								card.getId().toString() + ": question: " + card.getQuestion()
										+ " answer: " + card.getAnswer());
					}
				} else {
					resp.getWriter().println("Nothing found");
				}
			} finally {
				query.closeAll();
			}
		}
	}
}
