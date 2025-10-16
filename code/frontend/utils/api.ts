import { BACKEND_API } from "./constants";

export default async function apiAuthSignIn(
    credentials: Record<"email" | "username" | "password", string> | undefined
) {
    try {
        const response = await fetch(`${BACKEND_API}/api/auth/login`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                username: credentials?.username,
                password: credentials?.password
            }),
        });

        if (!response.ok) {
            return null;
        }

        const data = await response.json();

        if (data.error) {
            return null;
        }

        // Return user object with token
        return {
            id: data.username,
            name: data.username,
            email: credentials?.email,
            accessToken: data.token,
            userType: data.userType
        };
    } catch (error) {
        console.error("Auth error:", error);
        return null;
    }
}

export async function apiAuthSignUp(credentials: {
    username: string;
    email: string;
    password: string;
    fullName?: string;
    phoneNumber?: string;
    age?: number;
    role?: string;
    userType: 'admin' | 'chatter';
}) {
    try {
        const endpoint = credentials.userType === 'admin'
            ? `${BACKEND_API}/api/auth/register/admin`
            : `${BACKEND_API}/api/auth/register/chatter`;

        const response = await fetch(endpoint, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                username: credentials.username,
                email: credentials.email,
                password: credentials.password,
                fullName: credentials.fullName || "",
                phoneNumber: credentials.phoneNumber || "",
                age: credentials.age || 0,
                role: credentials.role || null
            }),
        });

        if (!response.ok) {
            const errorData = await response.json();
            return { error: errorData.error || "Sign-up failed" };
        }

        const data = await response.json();
        return data;
    } catch (error) {
        console.error("Sign-up error:", error);
        return { error: "Connection failed" };
    }
}
