import axios from "axios";
const BASE_URL = "/api/posts";
// 게시글 생성
export const createPost = async (post, token) => {
    const res = await axios.post(BASE_URL, post, {
        headers: { Authorization: `Bearer ${token}` },
        withCredentials: true,
    });
    return res.data;
};
// 게시글 목록 가져오기
export const getPosts = async (page = 1, size = 10) => {
    const token = localStorage.getItem("token");
    const res = await axios.get(`${BASE_URL}?page=${page}&size=${size}`, {
        headers: {
            Authorization: `Bearer ${token}`,
        },
        withCredentials: true,
    });
    return res.data;
};
