import React from 'react';
import ReactDOM from 'react-dom/client';
import {createBrowserRouter, Link, RouterProvider, useNavigate, useParams} from "react-router-dom";
import './index.css';
import reportWebVitals from './reportWebVitals';



function WelcomePage() {
    const navigate = useNavigate();

    const routeChange = () => navigate("/questions")
   // return <button onClick={routeChange} >HELLO ON THE WELCOME PAGE</button>
    return  <>
                <Link to={"/questions"}>
                    <button>Click On Me</button>
                </Link>
            </>
}

function MainPage() {
    return <div>WELLCOME TO THE MAIN PAGE</div>
}

function IdPage() {

    const {id} = useParams()
    console.log(id)
    return <div>hello user {id} </div>
}

const router = createBrowserRouter([
    {
        path:"/",
        element: <WelcomePage/>,
    },
    {
        path: "/questions",
        element: <MainPage />,
    },
    {
        path: "/questions/:id",
        element: <IdPage/>,
    }
]);

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <RouterProvider router = {router}/>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
