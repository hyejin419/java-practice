import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
export default function BoardWrite() {
    const [title, setTitle] = useState("");
    const [content, setContent] = useState("");
    const [image, setImage] = useState(null);
    const [message, setMessage] = useState("");
    const navigate = useNavigate();
    const token = localStorage.getItem("token");
    const handleSubmit = async () => {
        if (!title || !content) {
            setMessage("제목과 내용을 모두 입력하세요.");
            return;
        }
        const formData = new FormData();
        const post = { title, content };
        const blob = new Blob([JSON.stringify(post)], {
            type: "application/json",
        });
        formData.append("post", blob);
        if (image) {
            formData.append("image", image);
        }
        try {
            await axios.post("/api/posts", formData, {
                headers: {
                    Authorization: `Bearer ${token}`,
                    "Content-Type": "multipart/form-data",
                },
                withCredentials: true,
            });
            navigate("/board");
        } catch (e) {
            setMessage("글쓰기 실패 😥");
        }
    };
    return (
        <div>
            <h2>글쓰기</h2>
            <p>
                제목:{" "}
                <input
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}
                />
            </p>
            <p>
                내용:
                <br />
                <textarea
                    rows="8"
                    cols="50"
                    value={content}
                    onChange={(e) => setContent(e.target.value)}
                />
            </p>
            <p>
                이미지 첨부:{" "}
                <input
                    type="file"
                    accept="image/*"
                    onChange={(e) => setImage(e.target.files[0])}
                />
            </p>
            <button onClick={handleSubmit}>등록</button>
            <p>{message}</p>
        </div>
    );
}
