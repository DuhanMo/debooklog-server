const API_BASE_URL = process.env.REACT_APP_API_BASE_URL || 'http://localhost:8080';

export const fetchBookshelves = async () => {
    try {
        const response = await fetch(`${API_BASE_URL}/api/bookshelves`);
        if (!response.ok) {
            throw new Error('Failed to fetch bookshelves');
        }
        const result = await response.json();
        return result.data; // 🔹 data 속성만 반환
    } catch (error) {
        throw error;
    }
};
