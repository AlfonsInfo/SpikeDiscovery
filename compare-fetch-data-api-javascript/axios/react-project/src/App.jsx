import { useEffect, useState } from 'react';
import { client } from './api'
import './App.css'

function App() {
  const [posts,setPosts] = useState([]);

  useEffect(()=> {
      getPosts().then((val) => {
        setPosts(val.data)})
  },[])

  async function getPosts () {
    return await client.get("posts")
  }


  return (
    <div>
      {posts.map((val) => (
        <ul key={val.id}>
          <li>{val.title}</li> {/* or whatever property you want to display */}
        </ul>
      ))}
    </div>
  )
}

export default App
