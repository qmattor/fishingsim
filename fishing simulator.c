/*
** fishing simlator
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <time.h>
#define BUFF_SIZE 10

char	*strjoin(char *str1, char *str2)
{
	char	*newstr;
	char	*start;

	newstr = malloc(strlen(str1) + strlen(str2));
	start = newstr;
	while (*str1)
		*newstr++ = *str1++;
	while (*str2)
		*newstr++ = *str2++;
	return (start);
}

char	*strnew(int size)
{
	char	*str;

	str = malloc(size);
	bzero(str, size);
	return (str);
}

char	*getnextline()
{
	char	*buffer;
	char	*string;
	char	*freestuff;

	buffer = malloc(BUFF_SIZE);
	string = strnew(1);
	freestuff = string;
	while (read(1, buffer, BUFF_SIZE))
	{
		if (strchr(buffer, '\n') != NULL)
			*strchr(buffer, '\n') = 0;
		string = strjoin(string, buffer);
		free(freestuff);
		freestuff = string;
		if (strlen(buffer) != BUFF_SIZE)
			break ;
		bzero(buffer, BUFF_SIZE);
	}
	return (string);
}

int main()
{
	int	cast;
	int	otherthing;
	int	total;
	char	*string;

	cast = 0;
	total = 0;
	otherthing = 1;
	srand(time(NULL));
	printf("Its a beautiful day at you're local lake, will you [cast] your line?\n");
	while (otherthing)
	{
		string = getnextline();
		if (strcmp(string, "cast") == 0)
		{
			cast = rand() % 200;
			if (cast > 100)
			{
				printf("You caught a fish %i inches long\n", cast);
				total++;
			}
			else if (cast >= 50 && cast <= 100)
				printf("You didn't catch any fish\n");
			else
				printf("The fish was only %i inches long, so you had to throw it back\n", cast);
		}
		else
		{
			printf("You went home\n");
			otherthing = 0;
		}
		free(string);
	}
	printf("You caught %i fish today. You feel fulfilled in you're honest days work and decide to relax with a cold brewski\n", total);
	return 0;
}
