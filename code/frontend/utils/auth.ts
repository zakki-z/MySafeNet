import { NextAuthOptions, getServerSession } from "next-auth";
import CredentialsProvider from "next-auth/providers/credentials";
import apiAuthSignIn from "./api";

export const authOptions: NextAuthOptions = {
    providers: [
        CredentialsProvider({
            name: "Credentials",
            credentials: {
                username: { label: "Username", type: "text" },
                password: { label: "Password", type: "password" },
            },
            async authorize(credentials) {
                if (!credentials?.username || !credentials?.password) {
                    return null;
                }

                const user = await apiAuthSignIn({
                    username: credentials.username,
                    email: "", // Email not required for login
                    password: credentials.password
                });

                if (user && user.accessToken) {
                    return user;
                }
                return null;
            },
        }),
    ],
    callbacks: {
        async jwt({ token, user }) {
            if (user) {
                token.accessToken = user.accessToken;
                token.userType = user.userType;
                token.username = user.name;
            }
            return token;
        },
        async session({ session, token }) {
            session.accessToken = token.accessToken;
            session.userType = token.userType;
            session.user.name = token.username;
            return session;
        },
    },
    session: {
        strategy: "jwt",
        maxAge: 24 * 60 * 60, // 24 hours (match backend)
    },
    pages: {
        signIn: "/auth/signin",
    },
    secret: process.env.NEXTAUTH_SECRET as string,
};

export const getServerAuthSession = () => getServerSession(authOptions);
