import { useState } from 'react';
import api from '../services/api';
import AuthContext from './auth-context';

export const AuthProvider = ({ children }) => {
    const [user, setUser] = useState(() => {
        const token = localStorage.getItem('token');
        const name = localStorage.getItem('name');
        const role = localStorage.getItem('role');
        return token && name ? { token, name, role } : null;
    });

    const login = async (email, password) => {
        const response = await api.post('/auth/login', { email, password });
        const { token, name, role } = response.data;
        localStorage.setItem('token', token);
        localStorage.setItem('name', name);
        localStorage.setItem('role', role);
        setUser({ token, name, role });
    };

    const register = async (name, email, password) => {
        await api.post('/auth/register', { name, email, password });
    };

    const logout = () => {
        localStorage.removeItem('token');
        localStorage.removeItem('name');
        localStorage.removeItem('role');
        setUser(null);
    };

    return (
        <AuthContext.Provider value={{ user, login, register, logout, loading: false }}>
            {children}
        </AuthContext.Provider>
    );
};
