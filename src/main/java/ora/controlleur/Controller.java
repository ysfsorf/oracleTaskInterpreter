package ora.controlleur;

import java.util.Enumeration;

import javax.servlet.http.HttpSession;

import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ora.model.Code;
import ora.model.Resultat;

@RestController
public class Controller {

	@PostMapping("/execute")
	public Resultat get(@RequestBody Code code, HttpSession session) {
		PythonInterpreter interp = new PythonInterpreter();

		interp.exec("import sys");
		interp.exec("print sys");

		Enumeration<String> attributes = session.getAttributeNames();

		while (attributes.hasMoreElements()) {
			String attribute = (String) attributes.nextElement();

			interp.set(attribute, session.getAttribute(attribute));

		}

		if (code.getCode().contains("print")) {

			String code2[] = code.getCode().split(" ");
			try {

				interp.exec("x=" + code2[2]);

				PyObject x = interp.get("x");

				Resultat resultat = new Resultat();
				resultat.setResultat(x.toString());

				return resultat;
			} catch (Exception e) {
				Resultat resultat = new Resultat();
				resultat.setResultat("compileError");
				return resultat;
			}

		}

		else {

			String co = code.getCode().substring(8);
			if (co.contains("=")) {

				String code3[] = co.split(" ");
				try {
					interp.exec(co);
					PyObject x = interp.get(code3[0]);

					session.setAttribute(code3[0], x);
					Resultat resultat = new Resultat();
					resultat.setResultat("");
					return resultat;
				} catch (Exception e) {
					Resultat resultat = new Resultat();
					resultat.setResultat("compileError");
					return resultat;
				}

			} else {
				Resultat resultat = new Resultat();
				resultat.setResultat("");
				return resultat;
			}

		}

	}

}
