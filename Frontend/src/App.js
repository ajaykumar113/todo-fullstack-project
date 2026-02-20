import React, { useEffect, useState } from "react";
import axios from "axios";

const API_URL = "http://localhost:8080/api/todos";

function App() {
  const [todos, setTodos] = useState([]);
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [completed, setCompleted] = useState(false);

  useEffect(() => {
    fetchTodos();
  }, []);

  const fetchTodos = async () => {
    try {
      const response = await axios.get(API_URL);
      console.log("Data:", response.data);
      setTodos(response.data);
    } catch (error) {
      console.error("Error fetching todos:", error);
    }
  };

  const addTodo = async () => {
    if (!title) {
      alert("Title is required");
      return;
    }

    try {
      await axios.post(API_URL, {
        title,
        description,
        completed
      });

      setTitle("");
      setDescription("");
      setCompleted(false);
      fetchTodos();
    } catch (error) {
      console.error("Error adding todo:", error);
    }
  };

  const deleteTodo = async (id) => {
    try {
      await axios.delete(`${API_URL}/${id}`);
      fetchTodos();
    } catch (error) {
      console.error("Error deleting:", error);
    }
  };

  const toggleComplete = async (todo) => {
    try {
      await axios.put(`${API_URL}/${todo.id}`, {
        ...todo,
        completed: !todo.completed
      });
      fetchTodos();
    } catch (error) {
      console.error("Error updating:", error);
    }
  };

  return (
    <div style={{ padding: "20px" }}>
      <h2>Todo Application</h2>

      <input
        type="text"
        placeholder="Title"
        value={title}
        onChange={(e) => setTitle(e.target.value)}
      />
      <br /><br />

      <input
        type="text"
        placeholder="Description"
        value={description}
        onChange={(e) => setDescription(e.target.value)}
      />
      <br /><br />

      <label>
        Completed:
        <input
          type="checkbox"
          checked={completed}
          onChange={(e) => setCompleted(e.target.checked)}
        />
      </label>
      <br /><br />

      <button onClick={addTodo}>Add Todo</button>

      <hr />

      <ul>
        {todos.map((todo) => (
          <li key={todo.id}>
            <span
              style={{
                textDecoration: todo.completed ? "line-through" : "none",
                cursor: "pointer"
              }}
              onClick={() => toggleComplete(todo)}
            >
              {todo.title} - {todo.description}
            </span>

            <button
              style={{ marginLeft: "10px" }}
              onClick={() => deleteTodo(todo.id)}
            >
              Delete
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default App;