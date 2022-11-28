import { useNavigate } from 'react-router-dom'

export default function Register() {
    const navigate = useNavigate()
    return (
        <>
            <h1>Cadastro</h1>
            <button onClick={() => navigate(-1)}>Voltar</button>
        </>
    )
}
