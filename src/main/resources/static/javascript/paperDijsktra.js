document.getElementById("formTrajet").addEventListener("submit", afficheTrajet)


function afficheTrajet(event) {
    console.log("coucou")
    event.preventDefault();
	// Get a reference to the canvas object
	var canvas = document.getElementById('myCanvas')
	// Create an empty project and a view for the canvas:
	paper.setup(canvas);
	// Create a Paper.js Path to draw a line into it:
	var Path = new paper.Path();
	// Give the stroke a color
	Path.strokeColor = '#80ffdb'
	Path.strokeWidth = 10
	Path.strokeCap = 'round'
	Path.strokeJoin = 'round'
	
        let salle1 = document.getElementById("salle1").value;
        let salle2 = document.getElementById("salle2").value;
	// // Move to start and draw a line from there
	// path.moveTo(start);
	// // Note that the plus operator on Point objects does not work
	// // in JavaScript. Instead, we need to call the add() function:
	// path.lineTo(start.add([ 200, -50 ]));

	// Points position
	const pointsLocal = {
		A: [1230,800],
		B: [1130,870],
		C: [1300,850],
		D: [1300,930],
		E: [1380,840],
		F: [1380,920],
		G: [1440,820],
		H: [1450,900],
		I: [1540,800],
		J: [1590,870],
		nodeAB: [1230,865],
                nodeAB2: [1235,900],
		nodeCD: [1310,895],
		nodeEF: [1385,875],
		nodeGH: [1450,860],
		nodeIJ: [1560,840],

	};

	// Graph
	const graph = {
		A: {nodeAB: 1},
		B: {nodeAB: 1},
		C: {nodeCD: 1},
		D: {nodeCD: 1},
		E: {nodeEF: 1},
		F: {nodeEF: 1},
		G: {nodeGH: 1},
		H: {nodeGH: 1},
		I: {nodeIJ: 1},
		J: {nodeIJ: 1},
		nodeAB: {A: 1, B: 1, nodeAB2:1},
                nodeAB2: { nodeAB:1, nodeCD:1},
		nodeCD: {C: 1, D: 1, nodeAB2: 1, nodeEF: 1},
		nodeEF: {E: 1, F: 1, nodeCD: 1, nodeGH: 1},
		nodeGH: {G: 1, H: 1, nodeEF: 1, nodeIJ: 1},
		nodeIJ: {I: 1, J: 1, nodeGH: 1}
	};

	/**
	 *  Algorithme Dijkstra
	 */

	 const shortestDistanceNode = (distances, visited) => {
		let shortest = null;
	
		for (let node in distances) {
			let currentIsShortest =
				shortest === null || distances[node] < distances[shortest];
			if (currentIsShortest && !visited.includes(node)) {
				shortest = node;
			}
		}
		return shortest;
	};
	
	const findShortestPathWithLogs = (graph, startNode, endNode) => {
		// establish object for recording distances from the start node
		let distances = {};
		distances[endNode] = "Infinity";
		distances = Object.assign(distances, graph[startNode]);
	
		// track paths
		let parents = { endNode: null };
		for (let child in graph[startNode]) {
			parents[child] = startNode;
		}
	
		// track nodes that have already been visited
		let visited = [];
	
		// find the nearest node
		let node = shortestDistanceNode(distances, visited);
	
		// for that node
		while (node) {
			// find its distance from the start node & its child nodes
			let distance = distances[node];
			let children = graph[node];
			// for each of those child nodes
			for (let child in children) {
				// make sure each child node is not the start node
				if (String(child) === String(startNode)) {
					// console.log("don't return to the start node! 🙅");
					continue;
				} else {
					// console.log("startNode: " + startNode);
					// console.log("distance from node " + parents[node] + " to node " + node + ")");
					// console.log("previous distance: " + distances[node]);
					// save the distance from the start node to the child node
					let newdistance = distance + children[child];
					// console.log("new distance: " + newdistance);
					// if there's no recorded distance from the start node to the child node in the distances object
					// or if the recorded distance is shorter than the previously stored distance from the start node to the child node
					// save the distance to the object
					// record the path
					if (!distances[child] || distances[child] > newdistance) {
						distances[child] = newdistance;
						parents[child] = node;
						// console.log("distance + parents updated");
					} else {
						// console.log("not updating, because a shorter path already exists!");
					}
				}
			}
			// move the node to the visited set
			visited.push(node);
			// move to the nearest neighbor node
			node = shortestDistanceNode(distances, visited);
		}
	
		// using the stored paths from start node to end node
		// record the shortest path
		let shortestPath = [endNode];
		let parent = parents[endNode];
		while (parent) {
			shortestPath.push(parent);
			parent = parents[parent];
		}
		shortestPath.reverse();
	
		// return the shortest path from start node to end node & its distance
		let results = {
			distance: distances[endNode],
			path: shortestPath,
		};
	
		return results;
	};

	console.log(findShortestPathWithLogs(graph))

	console.log(pointsLocal[findShortestPathWithLogs(graph).path[1]])

	const dessinChemin = (startNode, endNode) => {
		var start = new paper.Point(pointsLocal[startNode]);
		const chemin = findShortestPathWithLogs(graph, startNode, endNode).path
		console.log(chemin)
	// Move to start and draw a line from there
		// Path.moveTo(start);
	// Note that the plus operator on Point objects does not work
	// in JavaScript. Instead, we need to call the add() function:
		for (let i = 0; i < chemin.length; i++) {
			Path.add(pointsLocal[chemin[i]])
			console.log(chemin[i])
		}
	}
	dessinChemin(salle1, salle2)

	// Draw the view now:
	paper.view.draw();
}

