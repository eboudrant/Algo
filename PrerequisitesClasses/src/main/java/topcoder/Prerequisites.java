package topcoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class Prerequisites {

	private class Program implements Comparable<Program> {
		private String name;
		private int number;
		private List<Program> prerequisites = new ArrayList<Program>();

		public Program(String name) {
			super();
			this.name = name;
			this.number = computeNumber(name);
		}

		public String getName() {
			return name;
		}

		public int getNumber() {
			return number;
		}

		public String toString() {
			StringBuffer buffer = new StringBuffer();
			for (Iterator<Program> iterator = prerequisites.iterator(); iterator
					.hasNext();) {
				Program sub = (Program) iterator.next();
				buffer.append(sub.getName());
				if (iterator.hasNext()) {
					buffer.append(" ");
				}
			}
			return getName() + "(" + getNumber() + ") " + buffer;
		}

		public int hashCode() {
			return getName().hashCode();
		}

		public int compareTo(Program o) {
			if (o == null) {
				return 1;
			}
			int result = getNumber() - o.getNumber();
			if (result == 0) {
				return getName().compareTo(o.getName());
			} else {
				return result;
			}
		}

		private int computeNumber(String programName) {
			char[] chars = programName.toCharArray();
			int result = 0;
			int i = 0;
			int nbchar = 0;
			boolean digit = false;
			while (i < chars.length) {
				if (chars[i] >= '0' && chars[i] <= '9') {
					digit = true;
					result *= 10;
					result += (chars[i] - '0');
				} else if (!(chars[i] >= 'A' && chars[i] <= 'Z')) {
					throw new RuntimeException(
							"Name must have only capital letters : "
									+ programName);
				} else if (digit) {
					throw new RuntimeException("Dept must be letter only : "
							+ programName);
				} else {
					nbchar++;
				}
				i++;
			}
			if (nbchar != 3 && nbchar != 4) {
				throw new RuntimeException(
						"Name must have 3 or 4 capital letters : "
								+ programName);
			}
			if (result > 999) {
				throw new RuntimeException("Class number too large : "
						+ programName);
			}
			if (result < 100) {
				throw new RuntimeException("Class number too small : "
						+ programName);
			}
			return result;
		}

		public void addPrereq(Program prereqProgram) {
			prerequisites.add(prereqProgram);
		}

		public void built() {
			Collections.sort(prerequisites);

		}

		public List<Program> getPrerequisites() {
			return prerequisites;
		}
	}

	public String[] orderClasses(String[] classes) {
		try {
			ArrayList<Program> prereq = new ArrayList<Prerequisites.Program>();
			HashMap<String, Program> map = new HashMap<String, Prerequisites.Program>();

			for (int i = 0; i < classes.length; i++) {
				
				if(classes[i].endsWith(" ")) {
					throw new RuntimeException("No trailing space allowed : '"
							+ classes[i] + "'");
				}

				String name = classes[i].substring(0, classes[i].indexOf(':'));
				Program program = map.get(name);
				if (program == null) {
					program = new Program(name);
					map.put(program.getName(), program);
				}

				String[] req = classes[i]
						.substring(classes[i].indexOf(':') + 1).trim()
						.split(" ");
				if (req.length > 0 && !"".equals(req[0])) {
					for (int j = 0; j < req.length; j++) {
						Program prereqProgram = map.get(req[j]);
						if (prereqProgram == null) {
							prereqProgram = new Program(req[j]);
							map.put(prereqProgram.getName(), prereqProgram);
						}
						program.addPrereq(prereqProgram);

					}
					prereq.add(program);
				} else {
					prereq.add(program);
				}
				program.built();
			}

			Collections.sort(prereq);

			ArrayList<Program> sorted = new ArrayList<Program>();
			sort(prereq.size(), new HashSet<String>(), new HashSet<String>(),
					prereq, sorted);

			String[] result = new String[sorted.size()];
			int i = 0;
			for (Iterator iterator = sorted.iterator(); iterator.hasNext();) {
				result[i++] = ((Program) iterator.next()).getName();
			}

			return result;
		} catch (RuntimeException rte) {
			System.err.println(rte.getMessage());
			return new String[0];
		}
	}

	private void sort(int count, HashSet<String> visited, HashSet<String> set,
			Collection<Program> toIterate, ArrayList<Program> sorted) {

		if (count == visited.size()) {
			return;
		}

		for (Iterator iterator = toIterate.iterator(); iterator.hasNext();) {
			Program program = (Program) iterator.next();
			visited.add(program.getName());
			if (program.getPrerequisites().size() == 0) {
				if (!set.contains(program.getName())) {
					sorted.add(program);
					set.add(program.getName());
				}
			} else {

				sort(count, visited, set, program.getPrerequisites(), sorted);

				if (count == visited.size()) {
					return;
				}
				if (!set.contains(program.getName())) {
					sorted.add(program);
					set.add(program.getName());
				}
			}
		}
	}

}
