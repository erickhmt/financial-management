import { useStore } from './hooks/useStore'
import './App.css'
import { GoogleLogin, GoogleOAuthProvider } from '@react-oauth/google'
import axios from 'axios'
import User from './components/User'

function App() {
  const setAuthData = useStore((state) => state.setAuthData)

  const handleLoginSuccess = async (response) => {
    console.log('Login success: ', response);
    const { data } = await axios.post("http://localhost:8080/login", {
      token: response.credential,
    });
    localStorage.setItem("AuthData", JSON.stringify(data));
    setAuthData(data);
  }

  return (
    <div className='App'>
      {!useStore((state) => state.authData) ? (
        <>
          <h1>Bem vindo(a)</h1>
          <p>Faça login para continuar</p>
          <GoogleOAuthProvider clientId={import.meta.env.VITE_GOOGLE_CLIENT_ID}>
          <GoogleLogin
            onSuccess={handleLoginSuccess}
            onFailure={(response) => console.log('Login failed: ', response)}
          />
          </GoogleOAuthProvider>
        </>
      ) : (
        <>
          <h1>Você está logado</h1>
          <User />
        </>
      )}

      
    </div>
  )
}

export default App
