import React from "react";
import { Routes, Route, Navigate } from "react-router-dom";
import Login from "./pages/Login";
import Register from "./pages/Register";
import Mypage from "./pages/Mypage";
import EditProfile from "./pages/EditProfile";
import BoardWrite from "./pages/BoardWrite";
import Board from "./pages/BoardList";

export default function App() {
    return (
        //return안에 화면 그리기
        <Routes>
            <Route path="/" element={<Navigate to="login" />} />
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} />
            <Route path="/mypage" element={<Mypage />} />
            <Route path="/editprofile" element={<EditProfile />} />
            <Route path="/board/write" element={<BoardWrite />} />
            <Route path="/board" element={<Board />} />
        </Routes>
    );
}
